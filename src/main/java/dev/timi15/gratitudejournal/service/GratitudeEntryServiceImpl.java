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
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class GratitudeEntryServiceImpl implements GratitudeEntryService {

    private final Firestore FIRESTORE;
    private final GratitudeEntryMapper gratitudeEntryMapper;
    private static final String COLLECTION_NAME = "GratitudeEntry";

    public List<GratitudeEntryResponseDTO> getAllGratitudeEntry() throws ExecutionException, InterruptedException {
        ApiFuture<QuerySnapshot> future = getCollectionReference().get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<GratitudeEntryResponseDTO> gratitudeEntries = new ArrayList<>();

        documents.forEach(doc -> {
            GratitudeEntryResponseDTO gratitudeEntry = gratitudeEntryMapper.toResponseDTO(doc.toObject(GratitudeEntry.class));
            gratitudeEntries.add(gratitudeEntry);
        });

        return gratitudeEntries;
    }

    public GratitudeEntryResponseDTO getGratitudeEntryById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = getCollectionReference().document(id);
        DocumentSnapshot document = docRef.get().get();

        if (!document.exists() && Objects.isNull(document.getData())) {
            throw new NotFoundException();
        } else {
            GratitudeEntry entity = document.toObject(GratitudeEntry.class);
            GratitudeEntryResponseDTO dto = gratitudeEntryMapper.toResponseDTO(entity);
            dto.setId(id);
            return dto;
        }
    }

    @Override
    public void createGratitudeEntry(GratitudeEntryRequestDTO gratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        if (null != gratitudeEntryRequestDTO.getId()) {
            DocumentReference docRef = getCollectionReference().document(gratitudeEntryRequestDTO.getId());
            DocumentSnapshot document = docRef.get().get();

            if (document.exists()) {
                throw new DuplicateException();
            }
            docRef.set(gratitudeEntryMapper.toEntity(gratitudeEntryRequestDTO));
        } else {
            getCollectionReference().add(gratitudeEntryMapper.toEntity(gratitudeEntryRequestDTO));
        }
        log.info("Created gratitude entry [{}]", gratitudeEntryRequestDTO);
    }

    @Override
    public void deleteGratitudeEntryById(String id) throws ExecutionException, InterruptedException {
        DocumentReference docRef = getCollectionReference().document(id);
        DocumentSnapshot document = docRef.get().get();

        if (!document.exists()) {
            throw new NotFoundException();
        }

        document.getReference().delete();
        log.info("Deleted [{}] gratitude entry.", id);
    }

    private CollectionReference getCollectionReference() {
        return FIRESTORE.collection(COLLECTION_NAME);
    }


}
