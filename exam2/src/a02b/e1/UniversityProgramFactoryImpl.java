package a02b.e1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import a02b.e1.UniversityProgram.Sector;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory{

    private class Course{
        Sector sector;
        int credits;

        public Course(final Sector sector, final int credits){
            this.sector = sector;
            this.credits = credits;
        }
    }

    private UniversityProgram generic(Predicate<List<Course>> pred){
        return new UniversityProgram() {

            Map<String, Course> courses = new HashMap<>();

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                courses.put(name, new Course(sector, credits));
            }

            private List<Course> toCourse(Set<String> courseNames){
                List<Course> toRet = new ArrayList<>();
                for(String s : courseNames){
                    toRet.add(courses.get(s));
                }
                return toRet;
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                return pred.test(toCourse(courseNames));
            }

            
            
        };
    }

    @Override
    public UniversityProgram flexible() {
        return generic((l)->l.stream().mapToInt(i->i.credits).sum()==60);
    }

    @Override
    public UniversityProgram scientific() {
        return generic((l)->l.stream().mapToInt(i->i.credits).sum()==60 && l.stream().filter(i->i.sector == Sector.MATHEMATICS).mapToInt(i->i.credits).sum()>=12 
            && l.stream().filter(i->i.sector == Sector.COMPUTER_SCIENCE).mapToInt(i->i.credits).sum()>=12
            && l.stream().filter(i->i.sector == Sector.PHYSICS).mapToInt(i->i.credits).sum()>=12);
    }

    @Override
    public UniversityProgram shortComputerScience() {
        return generic((l)->l.stream().mapToInt(i->i.credits).sum()>=48 && 
        l.stream().filter(i->i.sector == Sector.COMPUTER_ENGINEERING || i.sector == Sector.COMPUTER_SCIENCE).mapToInt(i->i.credits).sum()>=30);
    }

    @Override
    public UniversityProgram realistic() {
        return generic((l)->l.stream().mapToInt(i->i.credits).sum()==120 && 
        l.stream().filter(i->i.sector == Sector.COMPUTER_ENGINEERING || i.sector == Sector.COMPUTER_SCIENCE).mapToInt(i->i.credits).sum()>=60 && 
        l.stream().filter(i->i.sector == Sector.MATHEMATICS || i.sector == Sector.PHYSICS).mapToInt(i->i.credits).sum()<=18 && 
        l.stream().filter(i->i.sector == Sector.THESIS).mapToInt(i->i.credits).sum() == 24);
    }
    
}
