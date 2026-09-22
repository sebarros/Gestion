package cl.bitss.Gestion.service;
import cl.bitss.Gestion.model.Pedido;
import cl.bitss.Gestion.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository repository;
    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<Pedido> obtenerTodos() {
        return repository.findAll();
    }

    public Optional<Pedido> obtenerPorId(Long id) {
        return repository.findById(id);
    }

    public Pedido crear(Pedido pedido) {
        return repository.save(pedido);
    }

    public Optional<Pedido> actualizar(Long id, Pedido datos) {
        Optional<Pedido> pedidoEncontrado = repository.findById(id);
        if (pedidoEncontrado.isPresent()) {
            Pedido pedido = pedidoEncontrado.get();
            pedido.setUsuarioId(datos.getUsuarioId());
            pedido.setTotal(datos.getTotal());
            pedido.setEstado(datos.getEstado());
            return Optional.of(repository.save(pedido));
        }
        return Optional.empty();
    }

    public boolean eliminar(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Pedido> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }
}
