#!/usr/bin/env bash

javac -cp "lib/*" -d out $(find src -name "*.java")  

java -cp "out:lib/*" com.idc.reporter.Main   