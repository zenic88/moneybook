package api.damdev.moneybook.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import api.damdev.moneybook.domain.History;
import api.damdev.moneybook.dto.MoneyInfo;
import api.damdev.moneybook.repository.MoneyRepo;
import java.net.URI;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zenic
 * Created : 24/12/2018
 */
@Slf4j
@RestController("/api/moneybook/history")
public class HistoryController {

  private final MoneyRepo moneyRepo;

  private final ModelMapper modelMapper;

  public HistoryController(MoneyRepo moneyRepo, ModelMapper modelMapper) {
    this.moneyRepo = moneyRepo;
    this.modelMapper = modelMapper;
  }

  @PostMapping
  public ResponseEntity regHistory(MoneyInfo moneyInfo) {
    History history = modelMapper.map(moneyInfo, History.class);
    History newHistory = this.moneyRepo.save(history);
    URI createURL = linkTo(HistoryController.class).slash(newHistory.getId()).toUri();
    return ResponseEntity.created(createURL).body(newHistory);
  }

  @GetMapping
  public ResponseEntity getHistoryList() {
    List<History> history = moneyRepo.findAll();
    return ResponseEntity.ok(history);
  }

  @GetMapping("{historyId}")
  public ResponseEntity getHistory(@PathVariable String historyId) {
    History history = moneyRepo.findById(historyId).get();
    return ResponseEntity.ok(history);
  }

  @PutMapping("{historyId}")
  public ResponseEntity updateHistory(@PathVariable String historyId) {
    History history = moneyRepo.findById(historyId).get();
    log.info(history + "");
    return new ResponseEntity(HttpStatus.OK);
  }

  @DeleteMapping("{historyId}")
  public ResponseEntity deleteHistory(@PathVariable String historyId) {
    History history = moneyRepo.findById(historyId).get();
    log.info(history + "");
    return new ResponseEntity(HttpStatus.OK);
  }
}
