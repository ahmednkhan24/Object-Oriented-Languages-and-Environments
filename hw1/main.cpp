/*
    REFERENCES USED

    code done in class
    old code from previous classes
    http://www.cplusplus.com/reference/
    https://www.geeksforgeeks.org/vector-in-cpp-stl/
    https://codereview.stackexchange.com/questions/71799/effective-way-to-remove-white-spaces-from-string
    https://stackoverflow.com/questions/6444842/efficient-way-to-check-if-stdstring-has-only-spaces
    https://stackoverflow.com/questions/40984752/c-input-string-with-spaces
    https://www.youtube.com/watch?v=wRj9PZ2wyZI
    https://www.youtube.com/watch?v=FXhALMsHwEY
    https://www.youtube.com/watch?v=_r7i5X0rXJk
 */

#include <iostream>
#include <vector>
#include "employee.h"
#include "common.h"

using namespace std;

int main() {
    // get 0, 1, 2, 3
    int action;
    action = getValue();

    // list of all employees
    vector<employee> directory;

    // exit on 0
    while (action != 0) {
        if (action == 1) {
            loadDirectory(directory);
        }
        else if (action == 2) {
            addToDirectory(directory);
        }
        else {
            vector<employee> found = searchDirectory(directory);
            cout << "Found users:" << endl;
            for (employee e : found) {
                cout << e.getFirstName() << " " << e.getLastName() << endl;
            }
        }
        // get the next action command
        action = getValue();
    }
    cout << "Goodbye" << endl;
    return 0;
}