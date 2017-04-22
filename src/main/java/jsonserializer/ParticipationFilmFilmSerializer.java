package jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entities.ParticipationFilmEntity;

import java.io.IOException;
import java.util.List;


public class ParticipationFilmFilmSerializer extends StdSerializer<List<ParticipationFilmEntity>> {

    public ParticipationFilmFilmSerializer() {
        this(null);
    }

    public ParticipationFilmFilmSerializer(Class<List<ParticipationFilmEntity>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<ParticipationFilmEntity> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartArray();
        for(ParticipationFilmEntity participationFilmEntity : value){
            jgen.writeStartObject();
            jgen.writeNumberField("acteurId",Long.valueOf(participationFilmEntity.getActeur().getId()));
            jgen.writeStringField("acteurNom",participationFilmEntity.getActeur().getNom()+
            " "+participationFilmEntity.getActeur().getPrenom());
            jgen.writeStringField("importance",participationFilmEntity.getImportance().toString());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }
}
