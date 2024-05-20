insert into exams (id, title, min_point)
values (1, 'russianLanguage', 40);
insert into exams (id, title, min_point)
values (2, 'mathematics', 39);
insert into exams (id, title, min_point)
values (3, 'socialScience', 45);
insert into exams (id, title, min_point)
values (4, 'foreignLanguage', 30);
insert into exams (id, title, min_point)
values (5, 'informatics', 44);
insert into exams (id, title, min_point)
values (6, 'history', 35);
insert into exams (id, title, min_point)
values (7, 'biology', 39);

SELECT setval ('exams_id_seq', (SELECT MAX(id) FROM exams));

insert into specialities (id, title, exam_group, code)
values (1, 'Программная инженерия', 'rus_math_inf', '09.03.04');
insert into specialities (id, title, exam_group, code)
values (2, 'Продукты питания из растительного сырья', 'rus_math_bio', '19.03.02');
insert into specialities (id, title, exam_group, code)
values (3, 'Юриспруденция', 'rus_hist_social', '40.03.01');
insert into specialities (id, title, exam_group, code)
values (4, 'Информатика и вычислительная техника', 'rus_math_inf', '09.03.01');

SELECT setval ('specialities_id_seq', (SELECT MAX(id) FROM specialities));