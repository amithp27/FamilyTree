package Family;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.of;

import java.util.ArrayList;

import static Family.GetDetails.mother;
import static Family.GetDetails.father;
import static Family.GetDetails.spouse;
import static Family.GetDetails.children;
import static Family.GetDetails.siblings;
import static Family.GetDetails.sons;
import static Family.GetDetails.daughters;
import static Family.GetDetails.sisters;
import static Family.GetDetails.brothers;

// Amith M - 9035093734

public enum Relationships {
    //First connections
    MOTHER(member -> mother(of(member))),
    FATHER(member -> father(of(member))),
    SPOUSE(member -> spouse(of(member))),
    
    //Siblings
    BROTHER(member -> brothers(of(member))),
    SISTER(member -> sisters(of(member))),
    
    //Children
    SON(member -> sons(of(member))),
    DAUGHTER(member -> daughters(of(member))),
    CHILDREN(member -> children(of(member))),

    //Grand children
    GRANDSON(member -> SON.to(children(of(member)))),
    GRANDDAUGHTER(member -> DAUGHTER.to(children(of(member)))),

    //Cousins
    GRANDCHILDREN(member -> union(
            SON.to(children(of(member))),
            DAUGHTER.to(children(of(member))))),

    //Siblings
    SIBLINGS(member -> (siblings(of(member)))),

    //Cousins
    COUSIN(member -> union(
            CHILDREN.to(siblings(father(of(member)))),
            CHILDREN.to(siblings(mother(of(member)))))),

    //Inlaws
    BROTHER_IN_LAW(member -> union(
            BROTHER.to(spouse(of(member))),
            SPOUSE.to(sisters(of(member))))),
    SISTER_IN_LAW(member -> union(
            SISTER.to(spouse(of(member))),
            SPOUSE.to(brothers(of(member))))),

    //Paternal and Maternal Uncles/Aunts
    PATERNAL_UNCLE(member -> BROTHER.to(father(of(member)))),
    MATERNAL_UNCLE(member -> BROTHER.to(mother(of(member)))),
    PATERNAL_AUNT(member -> SISTER.to(father(of(member)))),
    MATERNAL_AUNT(member -> SISTER.to(mother(of(member))));
    
    private Function<Family.Member, Stream<Family.Member>> function;
    
    Relationships(Function<Family.Member, Stream<Family.Member>> func){
        this.function = func;
    }

    public Set<Family.Member> to(Family.Member member) {
        return this.function.apply(member).collect(toSet());
    }

    public Stream<Family.Member> to(Stream<Family.Member> members) {
        return members.map(this::to)
            .flatMap(Collection::stream);
    }

	private static Stream<Family.Member> union(Stream<Family.Member> members1, Stream<Family.Member> members2) {
        ArrayList<Stream<Family.Member>> arr = new ArrayList<Stream<Family.Member>>(Arrays.asList(members1));
        arr.addAll(Arrays.asList(members2));
        return arr.stream()
                .reduce(Stream::concat)
                .orElseThrow(RuntimeException::new);
    }
}