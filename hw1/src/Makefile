
output: main.o common.o employee.o
	g++ main.o common.o employee.o -o output
	./output

main.o: main.cpp
	g++ -c main.cpp

common.o: common.cpp common.h
	g++ -c common.cpp

employee.o: employee.cpp employee.h
	g++ -c employee.cpp

clean:
	rm *.o output