package dev.timi15.gratitudejournal.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import dev.timi15.gratitudejournal.dto.CreateGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.dto.GratitudeEntryResponseDTO;
import dev.timi15.gratitudejournal.dto.ModifyGratitudeEntryRequestDTO;
import dev.timi15.gratitudejournal.entity.GratitudeEntry;
import dev.timi15.gratitudejournal.exception.NoChangesDetectedException;
import dev.timi15.gratitudejournal.exception.NotFoundException;
import dev.timi15.gratitudejournal.mapper.GratitudeEntryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
@RequiredArgsConstructor
@Slf4j
public class GratitudeEntryServiceImpl implements GratitudeEntryService {

    private final Firestore FIRESTORE;
    private final GratitudeEntryMapper gratitudeEntryMapper;
    private static final String COLLECTION_NAME = "GratitudeEntry";
    private static final Long TEST_USER_ID = 1L;

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
    public GratitudeEntryResponseDTO createGratitudeEntry(CreateGratitudeEntryRequestDTO createGratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        GratitudeEntry gratitudeEntry = gratitudeEntryMapper.toEntity(createGratitudeEntryRequestDTO);
        DocumentReference docRef = getCollectionReference().add(gratitudeEntry).get();

        gratitudeEntry.setId(docRef.getId());
        gratitudeEntry.setUserId(TEST_USER_ID);

        log.info("Created gratitude entry [{}]", createGratitudeEntryRequestDTO);
        return gratitudeEntryMapper.toResponseDTO(gratitudeEntry);
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

    @Override
    public GratitudeEntryResponseDTO modifyGratitudeEntryById(String id, ModifyGratitudeEntryRequestDTO modifyGratitudeEntryRequestDTO) throws ExecutionException, InterruptedException {
        DocumentReference docRef = getCollectionReference().document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            throw new NotFoundException();
        }

        GratitudeEntry gratitudeEntry = gratitudeEntryMapper.toEntity(modifyGratitudeEntryRequestDTO);

        Map<String, Object> updates = new HashMap<>();

        Optional.ofNullable(gratitudeEntry.getContent())
                .ifPresent(content -> updates.put("content", content));

        Optional.ofNullable(gratitudeEntry.getDate())
                .ifPresent(date -> updates.put("date", date));

        if (updates.isEmpty()) {
            throw new NoChangesDetectedException();
        }

        gratitudeEntry.setId(docRef.getId());
        docRef.update(updates);
        log.info("Modified [{}] gratitude entry [{}]", id, modifyGratitudeEntryRequestDTO);
        return gratitudeEntryMapper.toResponseDTO(gratitudeEntry);
    }

    private CollectionReference getCollectionReference() {
        return FIRESTORE.collection(COLLECTION_NAME);
    }

}
