package dcc.ufjf.dcc193.debora.tomato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade, Long>{
    
}
