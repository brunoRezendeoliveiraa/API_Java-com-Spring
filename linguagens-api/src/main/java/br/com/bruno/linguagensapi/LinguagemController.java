package br.com.bruno.linguagensapi;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/*Por Default, o Spring já retorna formato JSON quando precisa retornar um objeto.*/

@RestController
public class LinguagemController {

	/*
	 * private List<LinguagemModel> linguagens = List.of(new LinguagemModel("Java",
	 * "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/java/java_256x256.png",
	 * 1), new LinguagemModel("PHP",
	 * "https://raw.githubusercontent.com/abrahamcalf/programming-languages-logos/master/src/php/php_256x256.png",
	 * 2));
	 */

	@Autowired
	private LinguagemRepository repositorio;

	@GetMapping("/linguagens")
	public List<LinguagemModel> resultadoLinguagens() {
		List<LinguagemModel> linguagens = repositorio.findAll();
		return linguagens;
	}

	@PostMapping("/linguagens")
	public LinguagemModel cadastrarLinguagem(@RequestBody LinguagemModel linguagem) {
		LinguagemModel linguagemSalva = repositorio.save(linguagem);
		return linguagemSalva;
	}

	@GetMapping("/linguagens/{id}")
	public LinguagemModel obterLinguagemId(@PathVariable String id) {
		return repositorio.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}

	@PutMapping("/linguagens/{id}")
	public LinguagemModel atualizarLinguagemId(@PathVariable String id, @RequestBody LinguagemModel linguagem) {
		if (!repositorio.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		linguagem.setId(id);
		LinguagemModel linguagemAtualizada = repositorio.save(linguagem);
		return linguagemAtualizada;
	}

	@DeleteMapping("/linguagens/{id}")
	public void deletarLinguagemId(@PathVariable String id) {
		repositorio.deleteById(id);
	}

	/*
	 * @GetMapping(value = "/linguagem-preferida") public String processaLinguagem()
	 * { return "Oi, Java 3!"; }
	 */

	/*
	 * @GetMapping("/linguagens") public List<LinguagemModel> resultadoLinguagens(){
	 * return linguagens; }
	 */

}
