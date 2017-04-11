package entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * Created by Jo on 10/04/2017.
 */
public class ParticipationSerializer extends StdSerializer<ParticipationFilmEntity> {

    public ParticipationSerializer() {
        this(null);
    }

    public ParticipationSerializer(Class<ParticipationFilmEntity> t) {
        super(t);
    }

    @Override
    public void serialize(
            ParticipationFilmEntity value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", value.getFilm().getId());
        jgen.writeStringField("titre", value.getFilm().getName());
        jgen.writeStringField("importance", value.getImportance().toString());
        jgen.writeEndObject();
    }
}
