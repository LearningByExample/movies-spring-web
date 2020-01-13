#!/bin/sh -

set -o errexit

if [ $# -ne 5 ]; then
  echo "Illegal number of parameters, usage : "
  echo " "
  echo "  $0 <service> <path> <users> <duration> <ramp up>"
  echo " "
  echo " example: "
  echo "  - $0 k8s-service /get/something 20 300 60"
  exit 2
fi

KUBECMD="kubectl"
if [ -x "$(command -v microk8s.kubectl)" ]; then
  KUBECMD="microk8s.kubectl"
fi

LOAD_DIR="$(
  cd "$(dirname "$0")"
  pwd -P
)"
REPORT_DIR="$LOAD_DIR/report"

if [ -d "$REPORT_DIR" ]; then
  echo "deleting report directory"
  rm -Rf "$REPORT_DIR"
  echo "report directory deleted"
fi

echo "creating report directory"
mkdir "$REPORT_DIR"
echo "report directory created"

SERVICE=$1
TEST_URL=$2
SERVICE_CLUSTER_IP=$($KUBECMD get "service/$SERVICE" -o jsonpath='{.spec.clusterIP}')
SERVICE_PORT=$($KUBECMD get "service/$SERVICE" -o jsonpath='{.spec.ports.*.targetPort}')
NUM_USERS=$3
DURATION=$4
RAMP_UP=$5

echo "launching a load test on http://$SERVICE_CLUSTER_IP:$SERVICE_PORT$TEST_URL with $NUM_USERS "\
  "user(s) during $DURATION seconds, ramping up during $RAMP_UP seconds"

HEAP="-Xms1g -Xmx1g -XX:MaxMetaspaceSize=256m"
jmeter -n -t "$LOAD_DIR/load_test.jmx" -l "$REPORT_DIR/run.jtl" -e -o "$REPORT_DIR" -j "$LOAD_DIR/jmeter.log" \
  -JSERVICE_CLUSTER_IP="$SERVICE_CLUSTER_IP" \
  -JSERVICE_PORT="$SERVICE_PORT" \
  -JNUM_USERS="$NUM_USERS" \
  -JDURATION="$DURATION" \
  -JRAMP_UP="$RAMP_UP" \
  -JTEST_URL="$TEST_URL"

echo "load test done"

WEB_REPORT_PATH="file:///$REPORT_DIR/index.html"
echo "report available on $WEB_REPORT_PATH"
