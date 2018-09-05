#include <iostream>

using namespace std;

#ifndef EMPLOYEE_H
#define EMPLOYEE_H

class employee {
private:
    string firstName;
    string lastName;
public:
    // constructors
    employee();
    employee(string first, string last);
    // getters
    string getFirstName();
    string getLastName();
    // setters
    void setFirstName(string first);
    void setLastName(string last);
};

#endif // EMPLOYEE_H