create table Course(
    courseId VARCHAR(6) Primary key,
    courseName VARCHAR(30),
    authorName VARCHAR(15),
    courseDuration BIGINT,
    courseAvailability BOOLEAN
);
select * from Course;