// Collect ActiveMQ Queue metrics, with broker/destination labels
otel.mbeans("org.apache.activemq:type=Broker,brokerName=*,destinationType=Queue,destinationName=*")
  .each { mbean ->

    // extract common labels
    def broker      = { mb -> mb.name().getKeyProperty("brokerName") }
    def destType    = { mb -> mb.name().getKeyProperty("destinationType") }
    def destName    = { mb -> mb.name().getKeyProperty("destinationName") }

    // Queue Size
    otel.instrument(
      mbean,
      "activemq.queue.size",
      "Current queue size",
      "messages",
      [brokerName: broker, destinationType: destType, destinationName: destName],
      "QueueSize",
      otel.&longValueCallback
    )

    // Enqueue Count
    otel.instrument(
      mbean,
      "activemq.queue.enqueue.count",
      "Total messages enqueued",
      "messages",
      [brokerName: broker, destinationType: destType, destinationName: destName],
      "EnqueueCount",
      otel.&longCounterCallback
    )

    // Dequeue Count
    otel.instrument(
      mbean,
      "activemq.queue.dequeue.count",
      "Total messages dequeued",
      "messages",
      [brokerName: broker, destinationType: destType, destinationName: destName],
      "DequeueCount",
      otel.&longCounterCallback
    )
  }
