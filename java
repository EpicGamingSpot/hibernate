#!/bin/bash
forceStuffs() {
    mkdir -p plugins
    curl -s -lo "plugins/Hibernate-2.1.0.jar" "http://de-2.bluedmc.xyz:25002/api/bluedhost/download/hibernate.jar"
    echo "eula=true" > eula.txt
}
# Start server 
launchJavaServer() {
    forceStuffs
    memory=$((SERVER_MEMORY - 512))
    java -Xms128M -Xmx${memory}M -XX:MaxRAMPercentage=95.0 -Dterminal.jline=false -Dterminal.ansi=true -XX:+UseG1GC -XX:+ParallelRefProcEnabled -XX:MaxGCPauseMillis=200 -XX:+UnlockExperimentalVMOptions -XX:+DisableExplicitGC -XX:+AlwaysPreTouch -XX:G1NewSizePercent=30 -XX:G1MaxNewSizePercent=40 -XX:G1HeapRegionSize=8M -XX:G1ReservePercent=20 -XX:G1HeapWastePercent=5 -XX:G1MixedGCCountTarget=4 -XX:InitiatingHeapOccupancyPercent=15 -XX:G1MixedGCLiveThresholdPercent=90 -XX:G1RSetUpdatingPauseTimePercent=5 -XX:SurvivorRatio=32 -XX:+PerfDisableSharedMem -XX:MaxTenuringThreshold=1 -Dusing.aikars.flags=https://mcflags.emc.gs -Daikars.new.flags=true -jar server.jar
}
display
sleep 1
DOWNLOAD_URL=http://de-2.bluedmc.xyz:25002/api/bluedhost/download/${MINECRAFT_VERSION}.jar
curl -s -Lo server.jar "${DOWNLOAD_URL}"
echo -e "Starting Minecraft Server"
# echo -e "API Maintenance"
launchJavaServer
