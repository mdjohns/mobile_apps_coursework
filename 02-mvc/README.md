# Assignment 2 - MVC Assignment

## Tasks

- [x] Define two attributes for the `Task` class: one to store the task description and a second one that indicates whether the task is done or not.

- [x] Define `Task` class constructor and the corresponding getters and setters.
- [x] Define `TaskList`'s attributes. The class will have just one attribute to store all the tasks created by the user.
- [x] Define `TaskList` class constructor and the corresponding getters and setters.
- [x] Add the definition of a `TaskList` member(attribute) to the `MainActivity`. Initialize the new member.
- [x] Define a new method called `add` in `TaskList` class that, given a task description, creates a new task and adds it to the task list.
- [x] Define a new method called `toString` in `TaskList` class that provides a formatted string with all the tasks in the task list.
- [x] Create a new method called `onAddBtnClicked` in `MainActivity` class to add a new task to the task list using the description provided through the text field on the top of the screen by tapping the `+` on the right.
- [x] Create a new method called `onDeleteBtnClicked` in `MainActivity` class to delete a task from the task list. The method will be invoked every time the user taps the DELETE button.
- [x] Define a new method called `delete` in TaskList class that, given a task id, deletes the corresponding task from the task list.
- [x] Use the pertinent methods from the TaskList class to implement `onDeleteBtnClicked` method's body in `MainActivity` class.
- [x] Create a new method called `onDone BtnClicked` in `MainActivity` class to mark a task as done. The method will be invoked every time the user taps the DONE button.
- [x] Define a new method called `markDone` in `TaskList` that, given a task id, marks the corresponding task as done.
- [x] Use the pertinent methods from the `TaskList` class to implement `onDoneBtnClicked` method's body in `MainActivity` class.

## Notes

- The implementation of `Task` and the associated methods should be straightforward.
- I added some error handling in the `TaskList` class. For `delete` and `markDone`, Android Studio warns you about potential null pointer exceptions if you don't check the value of `currentTask`. In the case that it is `null`, that means the provided `taskId` was invalid, and we throw an exception for that scenario.
- To implement the button methods, I added some helper methods as well:
  - `toggleKeyboard` seemed necessary since the soft keyboard does not close automatically after submitting. It is also helpful to be toggled when a Toast notification is displayed.
  - `displayError` creates a Toast notification to display any errors, particularly for the `delete` and `markDone` scenarios described above.
