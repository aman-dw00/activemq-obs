#!/bin/bash

echo "Creating 1000 destinations..."

for i in $(seq 1 10000); do
  curl -s -u admin:admin \
    "http://localhost:8161/api/message/TEST.QUEUE.$i?type=queue" \
    -H "Content-Type: text/plain" \
    -d "dummy message $i" > /dev/null
done

echo "✅ Done."
