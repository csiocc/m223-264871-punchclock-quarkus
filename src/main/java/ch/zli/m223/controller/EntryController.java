package ch.zli.m223.controller;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.dto.EntryDto;
import ch.zli.m223.model.Entry;
import ch.zli.m223.service.EntryService;

@Path("/entries")
@Tag(name = "Entries", description = "Handling of entries")
public class EntryController {

    @Inject
    EntryService entryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all Entries.", description = "Returns a list of all entries.")
    public List<Entry> index() {
        return entryService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new entry.", description = "Creates a new entry and returns the newly added entry.")
    public Entry create(@Valid @NotNull EntryDto dto) {
        Entry entry = toEntity(dto);
        return entryService.createEntry(entry);
        
    }

    @Path("/{id}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(Long id) {
        if (!entryService.deleteEntry(id)) {
            throw new NotFoundException();
        }
    }

    public Entry toEntity(EntryDto dto) {
        Entry entry = new Entry();
        entry.setCheckIn(dto.checkIn());
        entry.setCheckOut(dto.checkOut());
        return entry;
    }

    // public EntryDto toDto(Entry entry) {
    //    EntryDto dto = new EntryDto();
    //    dto.setId(entry.getId());
    //    dto.setCheckIn(entry.getCheckIn());
    //   dto.setCheckOut(entry.getCheckOut());
    //    return dto;
    //}

    @Path("/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Entry update(Long id, Entry entry) {
        return entryService.updateEntry(id, entry);
    }

}
