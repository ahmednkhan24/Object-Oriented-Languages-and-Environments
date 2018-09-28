#include <iostream>
#include "employee.h"

using namespace std;

// constructors
employee::employee() {
  firstName = "empty";
  lastName  = "empty";
}

employee::employee(string first, string last) {
  firstName = first;
  lastName  = last;
}

// getters
string employee::getFirstName() {
  return firstName;
}

string employee::getLastName() {
  return lastName;
}

// setters
void employee::setFirstName(string first) {
  firstName = first;
}

void employee::setLastName(string last) {
  lastName = last;
}