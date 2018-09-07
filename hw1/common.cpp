#include <iostream>
#include <algorithm>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <cctype>
#include "employee.h"

using namespace std;

/*
    Returns true if the number is out of range, false if not.
 */
bool invalidValue(int value, int min, int max) {
    if (value < min || value > max)
        return true;
    else
        return false;
}

/*
    Returns the number entered by the user
    Numbers can only either be 0, 1, 2, 3
 */
int getValue() {
    int action;
    do {
        cout << "Enter action number [0, 1, 2, or 3]: ";
        cin >> action;
    } while (invalidValue(action, 0, 3));
    return action;
}

/*
    Returns true if the file exists, false if not.
 */
bool fileExists(string filename) {
	ifstream file(filename);
	return file.good();
}

/*
    gets a filename from the user
    if the user does not add the .csv extension, 
    function will add it itself
 */
string getFilename() {
    string filename;
    cout << "Enter name of .csv file: ";
	cin >> filename;

    if (filename.substr(filename.length() - 4) != ".csv") {
        filename.append(".csv");
    }
    return filename;
}

/*
    User is prompted for the name of a comma separated value file (.csv),
    loads all the names from that file into the directory. 
    The csv contains only rows of the format LastName,FirstName.
*/
void loadDirectory(vector<employee>& directory) {
    string filename = getFilename();

	if (!fileExists(filename)) {
		cout << "Error: '" << filename << "' not found." << endl;
		return;
	}

    // let ifstream open and close the file
	ifstream file(filename);

    while (file.good()) {
        string first, last;

        // The csv contains only rows of the format LastName,FirstName
        getline(file, last, ',');
        getline(file, first, '\n');

        // remove the newline character at the end of the line
        // the last FirstName doesn't have a newline character at the end
        if (file.good()) {
            first.pop_back();
        }
        
        // erase all whitespace from the first and last names
        first.erase(remove(first.begin(), first.end(), ' '), first.end());
        last.erase(remove(last.begin(), last.end(), ' '), last.end());

        // create an employee object and add it to the directory ONLY if there is data in the first and last name fields
        if(first.find_first_not_of(' ') != std::string::npos && last.find_first_not_of(' ') != std::string::npos) {
            employee e(first, last);
            directory.push_back(e);
        }
    }
    return;
}

/*
    User is prompted for a name to enter into the directory. 
    This name should be in the format FirstName LastName. 
    No middle names or other information is entered at this time.
*/
void addToDirectory(vector<employee>& directory) {
    string first, last;
    cout << "Enter name of new employee (FirstName LastName): ";
    cin >> first >> last;

    employee e(first, last);
    directory.push_back(e);
}

/*
    creates a list of all employees who have the query string
    somewhere in his/her first or last name
*/
vector<employee> search(vector<employee>& directory, string query) {
    vector<employee> found;
    string tempFirst, tempLast;
    for (employee e : directory) {
        tempFirst = e.getFirstName();
        tempLast  = e.getLastName();

        // transform first name entirely to upper case letters
        transform(tempFirst.begin(), tempFirst.end(), tempFirst.begin(), ::toupper);
        int index1 = tempFirst.find(query);

        // transform last name entirely to upper case letters
        transform(tempLast.begin(), tempLast.end(), tempLast.begin(), ::toupper);
        int index2 = tempLast.find(query);
    
        if (index1 != -1 || index2 != -1) {
            found.push_back(e);
        }
    }
    return found;
}

/*
    creates a list of all employees who 
    have the first pattern in his/her first name
    and the last pattern in his/her last name
*/
vector<employee> searchAdvanced(vector<employee>& directory, string first, string last) {
    vector<employee> found;
    string tempFirst, tempLast;
    for (employee e : directory) {
        tempFirst = e.getFirstName();
        tempLast  = e.getLastName();

        // transform first name entirely to upper case letters
        transform(tempFirst.begin(), tempFirst.end(), tempFirst.begin(), ::toupper);
        int index1 = tempFirst.find(first);

        // transform last name entirely to upper case letters
        transform(tempLast.begin(), tempLast.end(), tempLast.begin(), ::toupper);
        int index2 = tempLast.find(last);

        if (index1 != -1 && index2 != -1) {
            found.push_back(e);
        }
    }
    return found;
}

/*
    User is prompted for a search query. 

    The sequence of characters they enter becomes a substring 
    to be matched against first and last names in the database.

    If there is a space in the sequence, the program searches for 
    a match among of the first name to the sequence leading up to 
    the space, and the last name to the sequence after the space. 
    
    All names in the database that match the query should be 
    printed out, in the format FirstName LastName, one per line.
*/
vector<employee> searchDirectory(vector<employee>& directory) {
    string query;
    cout << "Enter search query: ";
    cin.ignore();
    getline(cin, query, '\n');

    // transform query entirely to upper case letters
    transform(query.begin(), query.end(), query.begin(), ::toupper);

    int space = query.find(" ");
    vector<employee> found;

    // regular search
    if (space == -1) {
        found = search(directory, query);
    }
    // advanced search
    else {
        string first = query.substr(0, space);
        string last  = query.substr(space+1);

        last.erase(remove(last.begin(), last.end(), ' '), last.end());

        found = searchAdvanced(directory, first, last);
    }
    return found;
}