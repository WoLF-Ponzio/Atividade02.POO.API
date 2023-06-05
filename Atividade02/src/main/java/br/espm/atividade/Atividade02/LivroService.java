package br.espm.atividade.Atividade02;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
            livroRepository.findAll().forEach(l -> livros.add(new Livro(l.id_livro,l.nome_livro,l.autor_livro,l.editora_livro,l.ano_livro,l.preco_livro)));
        return livros;
    }

    public Livro dadosLivro(String id) {
        Livro livro = new Livro();
            livroRepository.findAll().forEach(l -> {
                if (l.id_livro.equals(id)) {
                    livro.id_livro = l.id_livro;
                    livro.nome_livro = l.nome_livro;
                    livro.autor_livro = l.autor_livro;
                    livro.editora_livro = l.editora_livro;
                    livro.ano_livro = l.ano_livro;
                    livro.preco_livro = l.preco_livro;
                }
            });
        return livro;
    }

    public List<Livro> buscarNomeAutor(String titulo){
        List<Livro> livros = new ArrayList<>();
        List<Livro> busca = new ArrayList<>();
            livroRepository.findAll().forEach(l -> livros.add(l));
            for (Livro livro : livros) {
                if (livro.nome_livro.contains(titulo) || livro.autor_livro.contains(titulo)) {
                    busca.add(livro);
                    break;
                }
            }
        return busca;
    }

    public void deletar(String id) {
        livroRepository.deleteById(id);
    }

    public Livro novoLivro(Livro livro) {
        LivroModel livModel = new LivroModel(livro);
        return livroRepository.save(livModel.newLiv());
    }

}
