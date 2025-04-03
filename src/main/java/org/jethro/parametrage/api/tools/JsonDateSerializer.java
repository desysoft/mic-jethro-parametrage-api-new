package org.jethro.parametrage.api.tools;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;

public class JsonDateSerializer extends JsonSerializer<LocalDate> {
  @Override
  public void serialize(LocalDate localDateTime, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {

    final String dateString = localDateTime.toString();
    jsonGenerator.writeString(dateString);
  }
}
