#include <iostream>
#include <vector>
#include "employee.h"

using namespace std;

int getValue();
void loadDirectory(vector<employee>& directory);
void addToDirectory(vector<employee>& directory);
vector<employee> searchDirectory(vector<employee>& directory);