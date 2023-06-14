package com.chekh.artsiom.initializer;

import com.chekh.artsiom.model.Department;
import com.chekh.artsiom.model.Student;
import com.chekh.artsiom.repository.DepartmentRepository;
import com.chekh.artsiom.repository.StudentRepository;
import javax.annotation.PostConstruct;
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
      department1.setDescription("Направления научных исследований:\n"
          + "1) Совершенствование геодезических методов мониторинга геодинамических процессов с привлечением глобальных моделей Земли и обеспечением многодисциплинарного подхода\n"
          + "2) Математическая обработка результатов геодезических измерений\n"
          + "3) Моделирование развития деформационных процессов природных и инженерных объектов по геопространственным данным с представлением в среде ГИС\n"
          + "4) Оптимизация обработки данных дистанционного зондирования в различных программных продуктах");
      departmentRepository.save(department1);

      Department department2 = new Department();
      department2.setName("Кафедра вычислительных систем и сетей");
      department2.setDescription("Направления научных исследований:\n"
          + "1) Разработка интеллектуальных информационных систем\n"
          + "2) Разработка методов и алгоритмов обработки статических и видеопоследовательностей\n"
          + "3) Разработка численных методов расчета строительных конструкций");
      departmentRepository.save(department2);

      Department department3 = new Department();
      department3.setName("Кафедра технологий программирования");
      department3.setDescription("Направления научных исследований:\n"
          + "1) Разработка программных и программно-аппаратных комплексов для решения прикладных задач\n"
          + "2) Анализ, проектирование и программное обеспечение информационных систем\n"
          + "3) Проектирование и разработка элементов смарт-систем\n"
          + "4) Компьютерное моделирование процессов в сложных системах");
      departmentRepository.save(department3);
    }
  }

}
