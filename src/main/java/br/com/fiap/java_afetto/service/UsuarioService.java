package br.com.fiap.java_afetto.service;

import br.com.fiap.java_afetto.dto.logradouro.LogradouroLista;
import br.com.fiap.java_afetto.dto.logradouro.LogradouroRequest;
import br.com.fiap.java_afetto.dto.logradouro.LogradouroResponse;
import br.com.fiap.java_afetto.dto.usuario.UsuarioRequest;
import br.com.fiap.java_afetto.dto.usuario.UsuarioResponse;
import br.com.fiap.java_afetto.mapper.LogradouroMapper;
import br.com.fiap.java_afetto.mapper.UsuarioMapper;
import br.com.fiap.java_afetto.model.Usuario;
import br.com.fiap.java_afetto.model.endereco.Bairro;
import br.com.fiap.java_afetto.model.endereco.Endereco;
import br.com.fiap.java_afetto.model.endereco.Logradouro;
import br.com.fiap.java_afetto.repository.UsuarioRepository;
import br.com.fiap.java_afetto.repository.endereco.BairroRepository;
import br.com.fiap.java_afetto.repository.endereco.EnderecoRepository;
import br.com.fiap.java_afetto.repository.endereco.LogradouroRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper, EnderecoRepository enderecoRepository) {
        this.usuarioMapper = usuarioMapper;
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }


    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Endereco endereco = enderecoRepository.findById(usuarioRequest.idEndereco())
                .orElseThrow(() -> new EntityNotFoundException("Endereco não encontrado"));

        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequest.nome());
        usuario.setCpf(usuarioRequest.cpf());
        usuario.setDataNascimento(usuarioRequest.dataNascimento());
        usuario.setEmail(usuarioRequest.email());
        usuario.setSenha(usuarioRequest.senha());
        usuario.setTelefone(usuarioRequest.telefone());
        usuario.setEndereco(endereco);

        return usuarioMapper.usuarioToResponse(usuarioRepository.save(usuario));
    }

//    public LogradouroResponse read(UUID id) {
//        Logradouro logradouro = logradouroRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Logradouro não encontrado"));
//        return logradouroMapper.logradouroToResponse(logradouro);
//    }
//
//    public Page<LogradouroLista> read(Pageable pageable) {
//        return logradouroRepository
//                .findAll(pageable)
//                .map(logradouroMapper::logradouroToResponseLista);
//    }
//
//    public LogradouroResponse update(UUID id, LogradouroRequest logradouroRequest) {
//        Logradouro logradouro = logradouroRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Logradouro não encontrado"));
//        BeanUtils.copyProperties(logradouroRequest, logradouro);
//        return logradouroMapper.logradouroToResponse(logradouroRepository.save(logradouro));
//    }
//
//    public void delete(UUID id) {
//        if (!logradouroRepository.existsById(id)) {
//            throw new EntityNotFoundException("Logradouro não encontrado");
//        }
//        logradouroRepository.deleteById(id);
//    }

}
