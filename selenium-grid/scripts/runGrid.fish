
#!/usr/bin/env fish

echo Hello Grid

# Stop any running Selenium processes
# pkill -9 -f selenium

# sleep 1

# Define the file path
set FILE_PATH $PWD/selenium-grid

# Start the Selenium hub
# java -jar "$FILE_PATH"/selenium-server-4.24.0.jar hub
# java -jar "$FILE_PATH"/selenium-server-4.24.0.jar node --hub http://localhost:4444
java -jar $FILE_PATH/selenium-server-4.24.0.jar hub --host localhost --port 4444 &

# sleep 3

sleep 5

