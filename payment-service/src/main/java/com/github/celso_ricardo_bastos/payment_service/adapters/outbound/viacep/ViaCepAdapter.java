package com.github.celso_ricardo_bastos.payment_service.adapters.outbound.viacep;

import com.github.celso_ricardo_bastos.payment_service.application.ports.outbound.AddressLookupOutboundPort;
import com.github.celso_ricardo_bastos.payment_service.dominio.model.Address;
import org.springframework.stereotype.Component;

@Component
public class ViaCepAdapter implements AddressLookupOutboundPort {

    private final ViaCepClient viaCepClient;

    public ViaCepAdapter(ViaCepClient viaCepClient) {
        this.viaCepClient = viaCepClient;
    }
    @Override
    public Address buscarCep(String cep) {
        ViaCepResponse response = viaCepClient.buscarCep(cep);
        return new Address(
                response.cep(),
                response.logradouro(),
                response.bairro(),
                response.localidade(),
                response.uf()
        );
    }
}
