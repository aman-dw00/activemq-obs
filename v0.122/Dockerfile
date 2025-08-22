FROM ubuntu:20.04

ENV DEBIAN_FRONTEND=noninteractive

# Install Java and tools
RUN apt-get update && apt-get install -y \
    openjdk-11-jre-headless \
    wget \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# Download and install the OpenTelemetry Collector
RUN wget https://github.com/open-telemetry/opentelemetry-collector-releases/releases/download/v0.132.1/otelcol-contrib_0.132.1_linux_amd64.tar.gz \
    && tar -xvzf otelcol-contrib_0.132.1_linux_amd64.tar.gz \
    && chmod +x otelcol-contrib \
    && mv otelcol-contrib /usr/local/bin/otelcontribcol \
    && rm otelcol-contrib_0.132.1_linux_amd64.tar.gz

# Set JAVA_HOME environment variable
ENV JAVA_HOME=/usr/lib/jvm/java-11-openjdk

CMD ["/usr/local/bin/otelcontribcol", "--config", "/etc/otel/config.yaml"]
