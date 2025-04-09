# AttendanceAppV2

## Overview
AttendanceAppV2 is a Java-based application designed to manage and track student attendance
in a course. It provides functionality to record attendance statistics such as on-time, late, excused, 
and unexcused counts for each student. The application ensures data validation for student details 
like seat numbers and names.

## Features
- **Student Management**: Add students with validated seat numbers and names.
- **Attendance Tracking**: Update and display attendance statistics for each student.
- **Equality Check**: Compare students based on their seat numbers.
- **Custom Display**: Generate formatted output for student and course details.

## Classes

### `Course`
- Represents a course containing students.
- Provides methods to manage and display course-related information.
- Overrides `toString()` to return the course name.

### `Student`
- Represents a student with attributes like seat number, name, and attendance stats.
- Provides methods to:
  - Get and set student details with validation.
  - Update attendance statistics.
  - Display attendance details.
  - Compare students based on seat numbers.
- Overrides `equals()` and `toString()` for custom behavior.

## Usage
1. **Add Students**: Create `Student` objects with valid seat numbers and names.
2. **Track Attendance**: Use `updateAttendance(int statsType)` to update attendance stats.
3. **Display Stats**: Call `displayAttendance()` to view a student's attendance details.
4. **Manage Courses**: Use the `Course` class to organize and display student data.

## Validation Rules
- **Seat Numbers**: Must be between 1 and 55 (inclusive).
- **Names**: Cannot be blank or consist of only whitespace.

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/isguil02/AttendanceAppV2
    ```
2. Open the project in your IDE.
3. Build the project to resolve dependencies.

### Running the Application
1. Navigate to the `AttendanceApp` class.
2. Run the `main` method to start the application.

## Example
![Example GIF](ex.gif)