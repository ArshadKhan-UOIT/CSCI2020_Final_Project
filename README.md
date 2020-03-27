# CSCI2020_Final_Project
2
​
3
Contributors and their Contributions:
4
​
5
Mario Velazquez(Mvtj22): Implemented a chat client/server between multiple users, contributed to half of MyGradesPage.java, wrote the CsvChanger Java file, designed the logo and wrote half of StartUp.java as well as all of ChangeGrade.java, and wrote parts of the README.md.
6
​
7
Tashdiq Ahmed(tashahmed): Wrote the SchedulePage java file, and wrote parts of the README.md.
8
​
9
Arshad Khan(ArshadKhan-UOIT): Wrote the CoursesPage java file, as well as half of MyGradesPage.java and implemented Gradle.
10
​
11
Ryan Christopher(Ryan-7926): Wrote Window.java, half of StartUp.java, created the main.java.CourseContent.DataStructures package, implemented AddInfo.java and its child classes, wrote RemoveEntry.java, Page Class.java, HomePage.java and laid the groundwork for Gradle implementation.
12
​
13
Repository URL: https://github.com/Ryan-7926/CSCI2020_Final_Project
14
​
15
Program Description:
16
This application was designed to serve as a companion tool to any student looking to organize their coursework. It has a page outlining each course alongside coursework and due dates with a visual of the mark breakup, a page that builds the student's day to day schedule of classes, a page that gives an in-depth look at the student's grades, the ability to edit the files that store all this information, and a chat system to facilitate communication between users.
17
​
18
Running the Program:
19
It is necessary for the CSV files to not only exist but also have sufficient data for the application to serve its function.
20
​
21
   -Startup:
22
   To obtain the files, download a ZIP of this repository, extract it, and open the project folder in Intellij. To run the file, the user must first run "StartChatServer," and then run "startup" in the upper right corner.
23
   The program will ask for a username that will eventually be used for the chat function, which is always available in the bottom right  corner. Upon entering a username, the user will be taken to the Home page.
24
   -Home page:
25
   On the left, the user is greeted with a list of upcoming assignments and tests, as well as a tiny description of their schedule for the day. On the right is a bar graph that outlines the mark breakup between assignments, midterms and exams. Along the top are buttons that will link the user to the other pages, as well as an extra function.
26
   -My Courses page:
27
   Underneath the main navigation bar is a smaller set of buttons that allow the user to display various information concerning each course. By clicking on a button, the course's course code, professor, and important dates are listed on the left. On the right is a simple pie chart that outlines the mark breakdown between the three categories of assignments, midterms and exams.
28
   -Schedule page:
29
   This page displays a schedule of all the user's classes that semester, by day and time. By clicking on a button labelled with a class name, the user can also see the professor's name as well as the time the class takes place.
30
   -My Grades page:
31
   A page that gives the user a more in-depth look at their grades and their breakdown. On the left, there are two tables; the top table is for the class that is determined by one of the smaller buttons the user presses at the top, and the bottom table is for all classes together. Both tables outline how many marks the user has gained, lost, or otherwise not encountered. On the right are pie charts associated with each table, putting their information in graphical format.
32
   -Edit Info:
33
   An extra function that allows the user to edit the CSVs. Instead of a page, it is a drop-down menu that allows a user to add a course, assignment, midterm, exam, or to-do list item, as well as change a grade or outright remove any of the above. Each option will open a new window, that has text fields allowing the user to input whatever they please.
34
   -Chat:
35
   A simple chatting interface that allows users to send messages to one another. The username the user entered on startup here is displayed alongside their message.
36
​
37
Gradle Tasks to run:
38
Gradle can be used to download the files, but gradle run is not functional due to time constraints.
