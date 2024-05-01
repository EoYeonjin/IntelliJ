package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {
    private final EntityManager em;

    public void save(Member member) {
        this.em.persist(member);
    }

    public Member findOne(Long id) {
        return (Member)this.em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return this.em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> findByName(String name) {
        return this.em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
    }

    public MemberRepository(final EntityManager em) {
        this.em = em;
    }
}
