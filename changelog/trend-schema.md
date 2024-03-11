# Changelog for trend-schema

## [Unreleased]

### Added
- New fields `type`, `showCardOnUi`, and `roles` to `FormSchema` entity.
- Method `findFormSchemasByTypeAndShowCardOnUi` in `FormSchemaRepository`.
- Method `getVisibleCardsForCurrentUser` in `FormSchemaService`.
- New API endpoint `/api/cards/visible` in `FormSchemaProviderController`.

## [0.1.0] - 2023-01-15
- Initial release of the application.