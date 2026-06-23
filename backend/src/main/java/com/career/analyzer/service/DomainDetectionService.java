package com.career.analyzer.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DomainDetectionService {

    public String detectDomain(
            List<String> skills) {

        int software = 0;
        int mechanical = 0;
        int civil = 0;
        int electrical = 0;
        int mba = 0;
        int finance = 0;
        int design = 0;

        for (String skill : skills) {

            // Software
            if (skill.equalsIgnoreCase("Java")
                    || skill.equalsIgnoreCase("Spring Boot")
                    || skill.equalsIgnoreCase("SQL")
                    || skill.equalsIgnoreCase("React")
                    || skill.equalsIgnoreCase("AWS")
                    || skill.equalsIgnoreCase("Docker")) {

                software++;
            }

            // Mechanical
            if (skill.equalsIgnoreCase("AutoCAD")
                    || skill.equalsIgnoreCase("SolidWorks")
                    || skill.equalsIgnoreCase("CATIA")) {

                mechanical++;
            }

            // Civil
            if (skill.equalsIgnoreCase("STAAD Pro")
                    || skill.equalsIgnoreCase("Revit")) {

                civil++;
            }

            // Electrical
            if (skill.equalsIgnoreCase("PLC")
                    || skill.equalsIgnoreCase("SCADA")) {

                electrical++;
            }

            // MBA
            if (skill.equalsIgnoreCase("Marketing")
                    || skill.equalsIgnoreCase("Sales")) {

                mba++;
            }

            // Finance
            if (skill.equalsIgnoreCase("Accounting")
                    || skill.equalsIgnoreCase("Tally")) {

                finance++;
            }

            // Design
            if (skill.equalsIgnoreCase("Figma")
                    || skill.equalsIgnoreCase("Photoshop")) {

                design++;
            }
        }

        int max = Math.max(
                software,
                Math.max(
                        mechanical,
                        Math.max(
                                civil,
                                Math.max(
                                        electrical,
                                        Math.max(
                                                mba,
                                                Math.max(finance, design)
                                        )
                                )
                        )
                )
        );

        if (max == software)
            return "Software Engineering";

        if (max == mechanical)
            return "Mechanical Engineering";

        if (max == civil)
            return "Civil Engineering";

        if (max == electrical)
            return "Electrical Engineering";

        if (max == mba)
            return "Business Administration";

        if (max == finance)
            return "Finance";

        return "Design";
    }
}