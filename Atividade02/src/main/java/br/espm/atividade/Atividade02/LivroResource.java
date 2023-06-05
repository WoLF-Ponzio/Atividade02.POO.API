package br.espm.atividade.Atividade02;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/livros")
public class LivroResource {

    private LivroService livroService;

    @Autowired
    public LivroResource(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping()
    public List<Livro> listarLivros(@RequestParam(name = "nome", defaultValue = "") String nome) {
        return livroService.listarLivros();
    }

    @GetMapping("/buscar/{id}")
    public Livro dadosLivro(@RequestParam String id) {
        return livroService.dadosLivro(id);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Livro>> buscarNomeAutor(@RequestParam String busca) {
        List<Livro> livrosEncontrados = livroService.buscarNomeAutor(busca);

        if (!livrosEncontrados.isEmpty()) {
            return ResponseEntity.ok(livrosEncontrados);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/post")
    public Livro adicionarLivro(@RequestBody Livro livro) {
        return livroService.novoLivro(livro);
    }
    
    @DeleteMapping("/{id}")
    public void deleteLivro(@PathVariable("id") String id) {
        livroService.deletar(id);
    }
}