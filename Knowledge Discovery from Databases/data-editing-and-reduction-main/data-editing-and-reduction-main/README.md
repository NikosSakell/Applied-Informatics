Knowledge Discovery from Databases:
  Programming Project 1 – Data Editing and Reduction
____________
Instructions:
  Choose your preferred programming language (Python, R, Java, C, Perl, SQL, etc) and implement three
  programs with the following requirements:
  1. Assume that the training datasets are csv files comprised of numerical attributes only with the 
  exception of the class attribute. You can also assume that the class attribute is the last attribute 
  of the file.
  2. Implement program NormalizeValues that takes as input a csv file and normalizes the values of
  all its attributes (i.e., transforms them in the [0, 1] range). Obviously, the class attribute should 
  be excluded. The normalized file should be written to disk.
  3. Implement program ENN that takes as input a normalized csv file and applies the editing 
  algorithm ENN on it. The edited file should be written to disk.
  4. Implement program IB2 that takes as input a normalized csv file and applies the instance 
  reduction algorithm IB2 on it. The reduced file should be written to disk.
  5. Test your implementations with the provided iris.csv and letter-recognition.csv datasets.
  Submit in a zip file with
  (a) your code (for compiled languages like Java, C, etc., also submit the executable files), 
  (b) the edited datasets (irisENN.csv and letter-recognitionENN.csv), and, 
  (c) the reduced datasets (irisIB2.csv and letter-recognitionIB2.csv).
___________
Execution instructions for Python code

Files:

Value normalization:
normalize-values.py

ENN Algorithm

Execute in the console, by typing after the .py file the path of the input file and the path of the output file:

e.g.
python enn.py NormalizeValuesOutput\iris-normalized-output.csv iris-enn-output.csv
python enn.py NormalizeValuesOutput\letter-recognition-normalized-output.csv letter-recognition-enn-output.csv

IB2 Algorithm

Execute in the console, the program will prompt the user to type the paths for the input and output files:

e.g.
python ib2.py
Enter the path to the input CSV file:
(type file-name.csv)
Enter the path to the output CSV file:
(type file-name.csv)
