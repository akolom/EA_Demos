package edu.mum.rest.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import edu.mum.domain.Category;
import jersey.repackaged.com.google.common.collect.Lists;

public class CategorySerializer extends JsonSerializer<List<Category>> {

    @Override
    public void serialize(final List<Category> categories, final JsonGenerator generator,
        final SerializerProvider provider) throws IOException, JsonProcessingException {
        final List<SimpleCategory> simpleCategories = Lists.newArrayList();
        for (final Category Category : categories) {
            simpleCategories.add(new SimpleCategory(Category.getId(), Category.getName()));                
        }
        generator.writeObject(simpleCategories);
    }

    static class SimpleCategory {

    	SimpleCategory(Long id, String name) {
    		this.id = id;
    		this.name = name;
    	}
        private Long id;

        private String name;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

         

    }


}
