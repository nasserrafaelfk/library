package io.pillopl.library.lending.application.holding

import io.pillopl.library.lending.application.PatronBooksFakeDatabase
import io.pillopl.library.lending.domain.patron.PatronId
import io.pillopl.library.lending.domain.patron.PatronInformation
import io.vavr.control.Option
import io.vavr.control.Try
import spock.lang.Specification

import static io.pillopl.library.lending.application.holding.PlacingOnHold.Result.Rejection
import static io.pillopl.library.lending.application.holding.PlacingOnHold.Result.Success
import static io.pillopl.library.lending.domain.book.BookFixture.anyBookId
import static io.pillopl.library.lending.domain.book.BookFixture.circulatingBook
import static io.pillopl.library.lending.domain.library.LibraryBranchFixture.anyBranch
import static io.pillopl.library.lending.domain.patron.PatronBooksEvent.PatronCreated.now
import static io.pillopl.library.lending.domain.patron.PatronBooksFixture.anyPatronId
import static io.pillopl.library.lending.domain.patron.PatronInformation.PatronType.Regular

class PlacingBookOnHoldTest extends Specification {

    PatronBooksFakeDatabase repository = new PatronBooksFakeDatabase()
    FindAvailableBook willFindBook = { id -> Option.of(circulatingBook()) }
    FindAvailableBook willNotFindBook = { id -> Option.none() }

    def 'should successfully place on hold book if patron and book exist'() {
        given:
            PatronId patron = persistedRegularPatron()
        and:
            PlacingOnHold holding = new PlacingOnHold(willFindBook, repository)
        when:
            Try<PlacingOnHold.Result> result = holding.placeOnHold(for3days(patron))
        then:
            result.isSuccess()
            result.get() == Success

    }


    def 'should reject placing on hold book if one of the domain rules is broken (but should not fail!)'() {
        given:
            PlacingOnHold holding = new PlacingOnHold(willFindBook, repository)
        and:
            PatronId patron = persistedRegularPatronWithManyHolds()
        when:
            Try<PlacingOnHold.Result> result = holding.placeOnHold(for3days(patron))
        then:
            result.isSuccess()
            result.get() == Rejection

    }

    def 'should fail if patron does not exists'() {
        given:
            PlacingOnHold holding = new PlacingOnHold(willFindBook, repository)
        and:
            PatronId patron = unknownPatron()
        when:
            Try<PlacingOnHold.Result> result = holding.placeOnHold(for3days(patron))
        then:
            result.isFailure()

    }


    def 'should fail if book does not exists'() {
        given:
            PlacingOnHold holding = new PlacingOnHold(willNotFindBook, repository)
        and:
            PatronId patron = persistedRegularPatron()
        when:
            Try<PlacingOnHold.Result> result = holding.placeOnHold(for3days(patron))
        then:
            result.isFailure()
    }

    PlaceOnHoldCommand for3days(PatronId patron) {
        return PlaceOnHoldCommand.closeEnded(patron, anyBranch(), anyBookId(), 4)
    }

    PatronId persistedRegularPatron() {
        PatronId patronId = anyPatronId();
        repository.handle(now(new PatronInformation(patronId, Regular)))
        return patronId
    }

    PatronId persistedRegularPatronWithManyHolds() {
        PatronId patronId = anyPatronId()
        repository.handle(new FakeTooManyHoldsEvent(patronId))
        return patronId
    }

    PatronId unknownPatron() {
        return anyPatronId()
    }

}

