package jsonserializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import entities.ParticipationFilmEntity;
import entities.ParticipationSerieEntity;

import java.io.IOException;
import java.util.List;

public class ParticipationSerieActeurSerializer extends StdSerializer<List<ParticipationSerieEntity>> {

    public ParticipationSerieActeurSerializer() {
        this(null);
    }

    public ParticipationSerieActeurSerializer(Class<List<ParticipationSerieEntity>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<ParticipationSerieEntity> value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartArray();
        for(ParticipationSerieEntity participationSerieEntity : value){
            jgen.writeStartObject();
            jgen.writeNumberField("filmId",Long.valueOf(participationSerieEntity.getSerie().getId()));
            jgen.writeStringField("filmNom",participationSerieEntity.getSerie().getNom());
            jgen.writeStringField("importance",participationSerieEntity.getImportance().toString());
            jgen.writeEndObject();
        }
        jgen.writeEndArray();
    }
}
