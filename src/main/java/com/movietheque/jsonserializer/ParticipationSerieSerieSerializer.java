package com.movietheque.jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.movietheque.entities.ParticipationSerieEntity;

import java.io.IOException;
import java.util.List;


public class ParticipationSerieSerieSerializer extends StdSerializer<List<ParticipationSerieEntity>> {

    public ParticipationSerieSerieSerializer() {
        this(null);
    }

    public ParticipationSerieSerieSerializer(Class<List<ParticipationSerieEntity>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<ParticipationSerieEntity> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartArray();
        for(ParticipationSerieEntity participationSerieEntity : value){
            jgen.writeStartObject();
            jgen.writeNumberField("acteurId",Long.valueOf(participationSerieEntity.getActeur().getId()));
            jgen.writeStringField("acteurNom",participationSerieEntity.getActeur().getNom()+
                    " "+participationSerieEntity.getActeur().getPrenom());
            jgen.writeStringField("importance",participationSerieEntity.getImportance().toString());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }
}
