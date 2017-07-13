package com.movietheque.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.movietheque.entities.ParticipationFilmEntity;

import java.io.IOException;
import java.util.List;

public class ParticipationFilmActeurSerializer extends StdSerializer<List<ParticipationFilmEntity>> {

    public ParticipationFilmActeurSerializer() {
        this(null);
    }

    public ParticipationFilmActeurSerializer(Class<List<ParticipationFilmEntity>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<ParticipationFilmEntity> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartArray();
        for(ParticipationFilmEntity participationFilmEntity : value){
            jgen.writeStartObject();
            jgen.writeNumberField("filmId",Long.valueOf(participationFilmEntity.getFilm().getId()));
            jgen.writeStringField("filmNom",participationFilmEntity.getFilm().getNom());
            jgen.writeStringField("importance",participationFilmEntity.getImportance().toString());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }
}
