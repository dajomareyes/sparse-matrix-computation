#!/bin/bash

# This script will simply compile the current java files save 
# the classes into a folder for that project step into a folder
# and then go into that folder and create zipped version of its contents
# making it easier for me to submit my homework for grading

echo starting submission creation process

# echo compiling files...
# javac -d submit *.java

echo copying java files into folder
cp *.java submit

echo copying input.txt file from my bin dir into submit dir 
cp bin/input.txt submit

echo creating zipped version of DIR
cd submit

# change the line below to create a new tar version for each project step
# tar -zcvf step-one.tar.gz *.java *.txt
tar -zcvf step-two.tar.gz *.java *.txt
# tar -zcvf step-three.tar.gz *.java *.txt
echo finished...

