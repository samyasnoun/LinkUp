package usecases.account_creation;

import entity.UserFactory;
import entity.User;

public class AccountCreationInteractor implements AccountCreationInputBoundary {
    final AccountCreationUserDataAccessInterface accountDataAccess;
    final AccountCreationOutputBoundary accountPresenter;
    final UserFactory userFactory;

    public AccountCreationInteractor(AccountCreationUserDataAccessInterface accountDataAccess,
                                     AccountCreationOutputBoundary accountPresenter,
                                     UserFactory userFactory) {
        this.accountDataAccess = accountDataAccess;
        this.accountPresenter = accountPresenter;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(AccountCreationInputData inputData){
        if (accountExists(inputData)) {
            accountPresenter.setFailView("Account Already Exists!");
        } else if (!passwordsEqual(inputData)){
            accountPresenter.setFailView("Passwords Do Not Match!");
        } else if (missingFields(inputData)) {
            accountPresenter.setFailView("Please Fill in All Fields!");
        } else {
            User user = userFactory.create(inputData.getUsername(), inputData.getPassword(), inputData.getLanguage());
            accountDataAccess.saveUser(user);
            AccountCreationOutputData outputData = new AccountCreationOutputData(user.getName(), user.getLanguage(), true);
            accountPresenter.setPassView(outputData);
        }
    }

    private boolean accountExists(AccountCreationInputData inputData) {
        return accountDataAccess.accountExists(inputData.getUsername());
    }

    private boolean passwordsEqual(AccountCreationInputData inputData) {
        return inputData.getPassword().equals(inputData.getRepeatedPassword());
    }

    private boolean missingFields(AccountCreationInputData inputData) {
        return (inputData.getUsername().isEmpty()
                || inputData.getPassword().isEmpty()
                || inputData.getRepeatedPassword().isEmpty()
                || inputData.getLanguage().isEmpty());
    }
}
