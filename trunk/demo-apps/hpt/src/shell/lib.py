import csv

def read(file):
	str='java -cp .;';
	reader=csv.reader(open(file));
	for line in reader:
		str+="dependency/"+line[0]+";";
	print str+' -jar ui_demo-1.0.jar';

if __name__=="__main__":
	read("f:/tmp/ui_demo/target/file.txt");
