package core.database;

import java.util.Collection;

import core.database.model.CompanyDO;
import core.database.model.Coupon;

public interface CompanyDAO {

	int createCompany(CompanyDO Company);

	int removeCompany(CompanyDO company);

	int updateCompany(CompanyDO Company);

	CompanyDO getCompany(long companyId);

	Collection<CompanyDO> getAllCompanies();

	Collection<Coupon> getCoupons(long companyId);

	boolean login(long companyId, String password);

}
