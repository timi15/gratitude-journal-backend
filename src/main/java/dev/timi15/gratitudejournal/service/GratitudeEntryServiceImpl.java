package dev.timi15.gratitudejournal.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.entity.GratitudeEntry;
import dev.timi15.gratitudejournal.exception.NotFoundException;
import dev.timi15.gratitudejournal.mapper.GratitudeEntryMapper;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GratitudeEntryServiceImpl implements GratitudeEntryService {

    private final Firestore FIRESTORE;
    private final GratitudeEntryMapper INSTANCE = Mappers.getMapper(GratitudeEntryMapper.class);
    private final String COLLECTION_NAME = "GratitudeEntry";

    public List<GratitudeEntryResponseDTO> getAllGratitudeEntry() throws ExecutionException, InterruptedException {
        CollectionReference gratitudeEntryRef = FIRESTORE.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = gratitudeEntryRef.get();
        List<QueryDocumentSnapshot> document = future.get().getDocuments();

        List<GratitudeEntryResponseDTO> gratitudeEntries = new ArrayList<>();

        document.forEach(doc -> {
            GratitudeEntryResponseDTO gratitudeEntry = INSTANCE.toResponseDTO(doc.toObject(GratitudeEntry.class));

            gratitudeEntries.add(gratitudeEntry);
        });

        return gratitudeEntries;
    }

    public GratitudeEntryResponseDTO getGratitudeEntryById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> gratitudeEntry = FIRESTORE.collection(COLLECTION_NAME).document(id).get();
        DocumentSnapshot document = gratitudeEntry.get();

        if (document.exists() && Objects.nonNull(document.getData())) {
            GratitudeEntry entity = document.toObject(GratitudeEntry.class);
            GratitudeEntryResponseDTO dto = INSTANCE.toResponseDTO(entity);
            dto.setId(id);
            return dto;
        } else {
            throw new NotFoundException();
        }
    }


}
