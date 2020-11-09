package com.bod.repository;

import com.bod.domain.User;
import com.bod.dto.UserStatDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface DoctorRepository extends JpaRepository<UserStatDto, Long> {
    @Query(
            value="SELECT \n" +
                    "c.`name`,\n" +
                    "dh_ave.avg_cal,\n" +
                    "dh_ave.avg_prot,\n" +
                    "dh_ave.avg_fat,\n" +
                    "dh_ave.avg_carb,\n" +
                    "f.name as favourite_food\n" +
                    "\tFROM\n" +
                    "\t\t`user` AS c \n" +
                    "   left outer join\n" +
                    "      (\n" +
                    "         SELECT\n" +
                    "            dh.deflection_records,\n" +
                    "            AVG(dh.calories) AS avg_cal,\n" +
                    "            AVG(dh.protein) AS avg_prot,\n" +
                    "            AVG(dh.fats) AS avg_fat,\n" +
                    "            AVG(dh.carbohydrates) AS avg_carb \n" +
                    "         from\n" +
                    "            deflection_record AS dh \n" +
                    "         group by\n" +
                    "            dh.deflection_records \n" +
                    "      )\n" +
                    "      AS dh_ave \n" +
                    "      ON dh_ave.deflection_records = c.id\n" +
                    "   left outer join\n" +
                    "      (\n" +
                    "         select\n" +
                    "            food_sums.food_records,\n" +
                    "            food_sums.foodId \n" +
                    "         from\n" +
                    "            (\n" +
                    "               select\n" +
                    "                  fh.food_records,\n" +
                    "                  fh.foodId,\n" +
                    "                  sum(fh.amount) as f_amount \n" +
                    "               from\n" +
                    "                  food_history fh \n" +
                    "               group by\n" +
                    "                  fh.food_records,\n" +
                    "                  fh.foodId \n" +
                    "            )\n" +
                    "            as food_sums \n" +
                    "         where\n" +
                    "            food_sums.f_amount = \n" +
                    "            (\n" +
                    "               select\n" +
                    "                  max(f_sums.f_amount) \n" +
                    "               from\n" +
                    "                  (\n" +
                    "                     select\n" +
                    "                        fh1.food_records, fh1.foodId, sum(fh1.amount) as f_amount \n" +
                    "                     from food_history fh1 \n" +
                    "                     \n" +
                    "\t\t\t\tgroup by\n" +
                    "                        fh1.food_records,\n" +
                    "                        fh1.foodId \n" +
                    "                  )\n" +
                    "                  as f_sums \n" +
                    "                  where f_sums.food_records = food_sums.food_records \n" +
                    "\t\t\t\tand f_sums.foodId = food_sums.foodId\n" +
                    "            )\n" +
                    "      ) as max_food on max_food.food_records = c.id \n" +
                    "    INNER JOIN food as f on f.food_id = max_food.foodId",
            nativeQuery=true

    )
    List<UserStatDto> readAllUsersStat();


}
