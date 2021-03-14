# DataBase

## Here is the thing that we are going to do with this project.
We will implement a student database app that will
- In first screen
    - list all the students in the database.
    - Provide option to add a new student into the database.
- Second screen
    - See individual user details in a separate screen.
    - update and delete the user data from the database.

### How are we going to do this you ask?
- Create a mainActivity and load/replace list and details fragment in the activity
    - Create a list fragment
	- fetch all data and show it in a recycler view.
	- provide a button to add a new student.
	- Clicking the add button should take the user to an data entry screen (details screen with empty data and a save button.)

- Create Student details fragment.
	- Two Edittext name and course.
	- Save/Update button.
	- Clicking the button in save mode will add a new row to the database and in update mode will update the given row witth new information provided by the user.
