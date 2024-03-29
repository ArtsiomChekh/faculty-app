package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.repository.DepartmentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentInitializer {

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostConstruct
    public void initialize() {
        if (departmentRepository.count() < 3) {
            Department department1 = new Department();
            department1.setName("Кафедра геодезии и геоинформационных систем");
            department1.setDescription("Кафедра геодезии и геоинформационных систем занимается изучением и применением "
                    + "методов и технологий для измерения, анализа и визуализации географической информации. Она "
                    + "объединяет знания из области геодезии, картографии, геоинформатики и геоинформационных систем. "
                    + "На кафедре геодезии и геоинформационных систем студенты изучают основы геодезии, включая теорию "
                    + "измерений, топографическое и инженерное обследование, геодезическую съемку и позиционирование. "
                    + "Они также получают знания о современных геоинформационных системах, которые позволяют собирать, "
                    + "хранить, анализировать и визуализировать географические данные.  Студенты имеют возможность "
                    + "практического применения своих знаний через выполнение проектов и исследований в области геодезии "
                    + "и геоинформационных систем. Кафедра также разрабатывает программы и методики для использования "
                    + "геоинформационных технологий в различных отраслях, таких как строительство, городское планирование,"
                    + "экология и многое другое.  Целью кафедры геодезии и геоинформационных систем является подготовка "
                    + "специалистов, обладающих не только техническими навыками в области сбора и обработки географической "
                    + "информации, но и способных применять эти знания для решения разнообразных задач в современном мире.");
            departmentRepository.save(department1);

            Department department2 = new Department();
            department2.setName("Кафедра вычислительных систем и сетей");
            department2.setDescription("Кафедра вычислительных систем и сетей занимается изучением и разработкой "
                    + "компьютерных систем, сетей и соответствующих технологий. Она объединяет знания из области "
                    + "программирования, компьютерных архитектур, операционных систем, сетевых протоколов и "
                    + "информационной безопасности.  На кафедре вычислительных систем и сетей студенты изучают "
                    + "основы программирования, алгоритмы и структуры данных, компьютерные сети, операционные системы "
                    + "и архитектуру компьютеров. Они также изучают методы и технологии разработки программного "
                    + "обеспечения, включая разработку веб-приложений, мобильных приложений и баз данных.  Студенты "
                    + "имеют возможность практического применения своих знаний через выполнение проектов и исследований "
                    + "в области вычислительных систем и сетей. Кафедра также занимается исследованиями в области "
                    + "новых технологий, таких как облачные вычисления, распределенные системы, интернет вещей и "
                    + "искусственный интеллект.  Целью кафедры вычислительных систем и сетей является подготовка "
                    + "специалистов, обладающих знаниями и навыками в области разработки программного обеспечения, "
                    + "администрирования компьютерных систем и проектирования сетей. Эти специалисты могут работать "
                    + "в различных отраслях, включая информационные технологии, телекоммуникации, финансы, "
                    + "здравоохранение и другие.");
            departmentRepository.save(department2);

            Department department3 = new Department();
            department3.setName("Кафедра технологий программирования");
            department3.setDescription("Кафедра технологии программирования специализируется на изучении и применении "
                    + "современных методов и технологий программирования. Она объединяет знания из области разработки "
                    + "программного обеспечения, программирования, алгоритмов и структур данных.  На кафедре технологии "
                    + "программирования студенты изучают различные языки программирования, включая основные концепции и "
                    + "синтаксис, а также специализированные инструменты и технологии разработки программного обеспечения. "
                    + "Они также изучают алгоритмы и структуры данных, которые являются основой эффективного программирования. "
                    + "Кафедра акцентирует внимание на анализе, проектировании и разработке информационных систем. "
                    + "Студенты получают знания о методах и инструментах для создания программного обеспечения, "
                    + "архитектуры приложений, баз данных и пользовательских интерфейсов.  Кроме того, кафедра "
                    + "занимается исследованиями в области разработки смарт-систем, которые объединяют в себе программное "
                    + "обеспечение и аппаратное обеспечение для создания интеллектуальных устройств и систем. Студенты "
                    + "изучают принципы работы смарт-систем, а также методы и подходы к их разработке.  Компьютерное "
                    + "моделирование процессов в сложных системах также является одной из областей исследования на кафедре. "
                    + "Студенты изучают методы и техники компьютерного моделирования для анализа и оптимизации процессов "
                    + "в различных областях, включая науку, инженерию и управление.  Целью кафедры технологии "
                    + "программирования является подготовка специалистов, обладающих глубокими знаниями и навыками в "
                    + "области программирования и разработки программного обеспечения. Эти специалисты могут работать "
                    + "в сфере разработки программного обеспечения, информационных технологий, научных исследований и "
                    + "других смежных областях.");
            departmentRepository.save(department3);
        }
    }
}
