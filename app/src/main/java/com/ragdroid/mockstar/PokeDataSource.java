package com.ragdroid.mockstar;

import com.ragdroid.mockstar.api.Pokemon;
import com.ragdroid.mockstar.api.PokemonService;
import com.ragdroid.mockstar.base.BaseSchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by garimajain on 07/03/17.
 */
public class PokeDataSource {

    private final PokemonService pokemonService;

    @Inject
    public PokeDataSource(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    public Observable<String> getPokemonStringObservable(String id) {
        return pokemonService.getPokemon(id)
                .map(new Function<Pokemon, String>() {
                    @Override
                    public String apply(@NonNull Pokemon pokemon) throws Exception {
                        return constructPokemon(pokemon);
                    }
                });
    }

    public Observable<String> getPokemonAbilityStringObservable(String id) {
        return pokemonService.getPokemon(id)
                .map(new Function<Pokemon, String>() {
                    @Override
                    public String apply(@NonNull Pokemon pokemon) throws Exception {
                        return constructAbility(pokemon);
                    }
                });
    }

    private static String constructAbility(Pokemon pokemon) {
        StringBuilder builder = new StringBuilder();
        builder.append(constructPokemon(pokemon));
        for (Pokemon.Ability ability : pokemon.getAbilities()) {
            builder.append("Ability Name : ")
                    .append(ability.getAbility().getName())
                    .append("\n Is Hidden : ")
                    .append(ability.getIsHidden());
        }
        return builder.toString();
    }

    private static String constructPokemon(Pokemon pokemon) {
        return "Id: " +
                pokemon.getId() +
                "\n" +
                "Name: " +
                pokemon.getName();

    }
}
