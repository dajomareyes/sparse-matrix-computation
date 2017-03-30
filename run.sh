#!/bin/bash
# This script is used to compile all code inside this folder
# Then it runs the program

javac -d bin *.java 
read -p "Are you sure you want to continue? <y/N> " prompt
if [[ $prompt == "y" || $prompt == "Y" || $prompt == "yes" || $prompt == "Yes" ]] 
then
    cd bin
    java MatrixComputation
else
    exit 0
fi
