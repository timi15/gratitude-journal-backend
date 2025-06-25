package dev.timi15.gratitudejournal.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import dev.timi15.gratitudejournal.dto.GratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.entity.GratitudeEntry;
import dev.timi15.gratitudejournal.exception.DuplicateException;
import dev.timi15.gratitudejournal.exception.NotFoundException;
import dev.timi15.gratitudejournal.mapper.GratitudeEntryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
public class GratitudeEntryServiceImpl implements GratitudeEntryService {

    private final Firestore FIRESTORE;
    private final GratitudeEntryMapper gratitudeEntryMapper;
    private final String COLLECTION_NAME = "GratitudeEntry";

    public List<GratitudeEntryResponseDTO> getAllGratitudeEntry() throws ExecutionException, InterruptedException {
        CollectionReference collectionRef = FIRESTORE.collection(COLLECTION_NAME);
        ApiFuture<QuerySnapshot> future = collectionRef.get();
        List<QueryDocumentSnapshot> document = future.get().getDocuments();

        List<GratitudeEntryResponseDTO> gratitudeEntries = new ArrayList<>();

        document.forEach(doc -> {
            GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryMapper.toResponseDTO(doc.toObject(GratitudeEntry.class));
            gratitudeEntries.add(gratitudeEntry);
        });

        return gratitudeEntries;
    }

    public GratitudeEntryResponseDTO getGratitudeEntryById(String id) throws ExecutionException, InterruptedException {
        ApiFuture<DocumentSnapshot> future = FIRESTORE.collection(COLLECTION_NAME).document(id).get();
        DocumentSnapshot document = future.get();

        if (document.exists() && Objects.nonNull(document.getData())) {
            GratitudeEntry entity = document.toObject(GratitudeEntry.class);
            GratitudeEntryResponseDTO dto = gratitudeEntryMapper.toResponseDTO(entity);
            dto.setId(id);
            return dto;
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public void createGratitudeEntry(GratitudeEntryRequestDTO gratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        if (null != gratitudeEntryRequestDTO.getId()) {
            DocumentReference docRef = FIRESTORE.collection(COLLECTION_NAME).document(gratitudeEntryRequestDTO.getId());
            DocumentSnapshot document = docRef.get().get();

            if (document.exists()) {
                throw new DuplicateException();
            }
            docRef.set(gratitudeEntryMapper.toEntity(gratitudeEntryRequestDTO));
        } else {
            FIRESTORE.collection(COLLECTION_NAME).add(gratitudeEntryMapper.toEntity(gratitudeEntryRequestDTO));
        }

    }


}
